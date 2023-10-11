require("@nomicfoundation/hardhat-toolbox");

const ALCHEMY_URL = "https://eth-sepolia.g.alchemy.com/v2/rwHETGslhGFm8yUHbrOv3IvyHs2VRDGt";
const METAMASK_URL = "311257c97980c77bab83de5cb567500df4d51e20d95e07ffc9daddd102b45c91"

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.19",
  networks: {
    sepolia: {
      url: ALCHEMY_URL,
      accounts: [`0x${METAMASK_URL}`]
    },
  },
  
};
