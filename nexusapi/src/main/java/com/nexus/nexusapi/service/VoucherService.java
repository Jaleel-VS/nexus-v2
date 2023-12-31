package com.nexus.nexusapi.service;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.dto.request.VoucherDTO;
import com.nexus.nexusapi.dto.response.VoucherResponseDTO;
import com.nexus.nexusapi.exceptions.NotFoundException;
import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.model.Voucher;
import com.nexus.nexusapi.model.VoucherRequest;
import com.nexus.nexusapi.repository.BrandRepository;
import com.nexus.nexusapi.repository.InfluencerRepository;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.repository.SupplierRepository;
import com.nexus.nexusapi.repository.VoucherRepository;
import com.nexus.nexusapi.repository.VoucherRequestRepository;
import com.nexus.nexusapi.web3.Web3Manager;

@Service
public class VoucherService {

        @Autowired
        private VoucherRepository voucherRepository;

        @Autowired
        private BrandRepository brandRepository;

        @Autowired
        private InfluencerRepository influencerRepository;

        @Autowired
        private SupplierRepository supplierRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private VoucherRequestRepository voucherRequestRepository;

        @Autowired
        private Web3Manager web3Manager;

        // create voucher
        public VoucherResponseDTO createVoucher(VoucherDTO voucherDTO) {
                Voucher voucher = new Voucher();

                VoucherRequest voucherRequest = voucherRequestRepository
                                .findById(Long.parseLong(voucherDTO.getVoucherRequestID()))
                                .orElseThrow(() -> new NotFoundException("Voucher Request not found"));

                Brand brand = brandRepository.findById(voucherRequest.getBrand().getId())
                                .orElseThrow(() -> new NotFoundException("Brand not found"));

                Influencer influencer = influencerRepository.findById(voucherRequest.getInfluencer().getId())
                                .orElseThrow(() -> new NotFoundException("Influencer not found"));

                Product product = productRepository.findById(voucherRequest.getProduct().getId())
                                .orElseThrow(() -> new NotFoundException("Product not found"));

                voucher.setVoucherRequest(voucherRequest);

                voucher.setBrand(brand);
                voucher.setInfluencer(influencer);
                voucher.setProduct(product);

                voucher.setCreatedDate(java.time.LocalDate.now());

                Long expiryDateInSeconds = voucherDTO.getExpiryDate();
                if (expiryDateInSeconds != null) {
                      // Convert the epoch timestamp to Instant
                        Instant instant = Instant.ofEpochMilli(expiryDateInSeconds);

                        // Define the time zone you want to use
                        ZoneId zoneId = ZoneId.of("UTC"); // You can change this to your desired time zone

                        // Convert Instant to LocalDate using the defined time zone
                        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

                        voucher.setExpiryDate(localDate);
                } else {
                        voucher.setExpiryDate(LocalDate.ofEpochDay(0));
                }

                String recipientAddress = influencer.getMetamaskAddress();

                voucher.setRedeemed(false);

                ArrayList<String> blockchainUrl = mintVoucher(influencer, brand, product, expiryDateInSeconds,
                                recipientAddress);

                voucher.setBlockchainUrl(blockchainUrl.get(0));
                voucher.setVoucherContractID(Integer.parseInt(blockchainUrl.get(1)));
                
                UUID uuid = UUID.randomUUID();

                String voucherQRCodeString = uuid.toString().split("-")[0];

                voucher.setVoucherQRCodeString(voucherQRCodeString);

                voucherRepository.save(voucher);

                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setId(voucher.getId());
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                voucherResponseDTO.setBlockchainURL(voucher.getBlockchainUrl());
                voucherResponseDTO.setVoucherContractID(voucher.getVoucherContractID());
                voucherResponseDTO.setVoucherQRCodeString(voucher.getVoucherQRCodeString());

                return voucherResponseDTO;
        }

        private ArrayList<String> mintVoucher(Influencer influencer, Brand brand, Product product,
                        Long expiryDate, String requestAddress) {
                String influencerID = influencer.getUsername().toString();
                String brandID = brand.getBrandName().toString();
                String productID = product.getProductName().toString();
                // use streams, with a map function to convert the list of suppliers to a
                // string, and have a comma
                String supplierIDs = product.getSuppliers().stream()
                                .map(supplier -> supplier.getSupplierName().toString())
                                .reduce("", (a, b) -> a + "," + b);
                

                Integer price = product.getProductPrice();

                ArrayList<String> blockchainUrl = web3Manager.mintVoucher(
                                influencerID,
                                brandID,
                                supplierIDs,
                                productID,
                                price,
                                expiryDate,
                                requestAddress);

                // System.out.println(supplierIDs);

                return blockchainUrl;
        }

        // get voucher by id
        public VoucherResponseDTO getVoucherById(Long id) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                return voucherResponseDTO;
        }

        // get voucher by request id
        public VoucherResponseDTO getVoucherByRequestId(Long id) {
                Voucher voucher = voucherRepository.findByVoucherRequest_Id(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));

                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setId(voucher.getId());
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                voucherResponseDTO.setBlockchainURL(voucher.getBlockchainUrl());
                voucherResponseDTO.setVoucherContractID(voucher.getVoucherContractID());
                voucherResponseDTO.setVoucherQRCodeString(voucher.getVoucherQRCodeString());

                return voucherResponseDTO;
        }

        // update voucher status
        public VoucherResponseDTO updateVoucherStatus(Long id, boolean status) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                voucher.setRedeemed(status);
                voucherRepository.save(voucher);
                VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                voucherResponseDTO.setProductId(voucher.getProduct().getId());
                voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                return voucherResponseDTO;
        }

        // check if voucher is redeemed
        public boolean isRedeemed(Long id) {
                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));
                return voucher.isRedeemed();
        }

        public Boolean redeemVoucher(String voucherPin, Long supplierId) {



                Voucher voucher = voucherRepository.findByVoucherQRCodeString(voucherPin)
                                .orElseThrow(() -> new NotFoundException("Voucher not found"));


                String suppliers = voucher.getProduct().getSuppliers().stream()
                                .map(supplier -> supplier.getSupplierName().toString())
                                .reduce("", (a, b) -> a + "," + b);

                // check if supplier is in the list of suppliers for the voucher/product
                if (!suppliers.contains(supplierRepository.findById(supplierId).get().getSupplierName().toString())) {
                        return false;
                }

                String result = web3Manager.redeemVoucher(
                                BigInteger.valueOf(voucher.getVoucherContractID()),
                                supplierRepository.findById(supplierId).get().getSupplierName().toString(),
                                voucher.getBrand().getBrandName(),
                                voucher.getInfluencer().getUsername(),
                                voucher.getProduct().getProductName(),
                                voucher.getProduct().getProductPrice(),
                                voucher.getExpiryDate().toEpochDay());

                

                if (result != null) {
                        voucher.setRedeemed(true);
                        voucherRepository.save(voucher);
                        return true;
                }

                return false;
        }



        public Iterable<VoucherResponseDTO> getAllVouchers() {
                Iterable<Voucher> vouchers = voucherRepository.findAll();
                ArrayList<VoucherResponseDTO> voucherResponseDTOs = new ArrayList<>();

                for (Voucher voucher : vouchers) {
                        VoucherResponseDTO voucherResponseDTO = new VoucherResponseDTO();
                        voucherResponseDTO.setId(voucher.getId());
                        voucherResponseDTO.setBrandId(voucher.getBrand().getId());
                        voucherResponseDTO.setInfluencerId(voucher.getInfluencer().getId());
                        voucherResponseDTO.setProductId(voucher.getProduct().getId());
                        voucherResponseDTO.setCreationDate(voucher.getCreatedDate());
                        voucherResponseDTO.setExpiryDate(voucher.getExpiryDate());
                        voucherResponseDTO.setRedeemed(voucher.isRedeemed());
                        voucherResponseDTO.setBlockchainURL(voucher.getBlockchainUrl());
                        voucherResponseDTO.setVoucherContractID(voucher.getVoucherContractID());
                        voucherResponseDTO.setVoucherQRCodeString(voucher.getVoucherQRCodeString());
                        voucherResponseDTOs.add(voucherResponseDTO);
                }

                return voucherResponseDTOs;
        }
}
