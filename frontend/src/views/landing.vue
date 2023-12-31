<template>
    <div class="landing-page">
        <nav class="nav-bar">
            <ul>
                <li><router-link to="/about"><h3>About</h3></router-link></li>
                <li><router-link to="/faq"><h3>FAQ Section</h3></router-link></li>
                <li><router-link to="/contact"><h3>Contact</h3></router-link></li>
            </ul>
        </nav>
        
        <div class="content-wrapper">
            <div class="text-section">
                <h1>Nexus: <br> The three-way blockchain contract</h1>
                <p class="intro">
                    Seamless transactions, guaranteed trust, and automated smart contracts—all made possible with our innovative crypto voucher system. Dive into the future of trusted business, where every party's interest is safeguarded.
                </p>
                <div class="buttons">
                    <button @click="displayLoginPage" class="sign-in-btn">Sign In</button>
                    <router-link to="/application">
                        <button class="apply-btn">Apply</button>
                    </router-link>
                    
                </div>
            </div>
            <div class="image-section">
                <img src="https://static.vecteezy.com/system/resources/previews/010/842/435/original/3d-illustration-ethereum-logo-png.png"
                    alt="Hero Image" />
            </div>
        </div>
        <header class="header">
            <h2>Why Nexus?</h2>
        </header>
        <div class="image-container">
            <div class="image-wrapper">
                <img src="../assets/landing/catalogue.png" alt="Image 1">
                <h3><p class="broadheader">Broad Catalogue</p></h3>
                <p class="broad">Extensive merchant network gives you direct access to hundreds of premier global brands across multiple sectors.</p>
            </div>
            <div class="image-wrapper">
                <img src="../assets/landing/tracking.png" alt="Image 2">
                <h3><p class="broadheader">Tracking and Reporting</p></h3>
                <p class="broad">Blockchain technology allows you to easily manage and track all your vouchers and progress in one place.</p>
            </div>
            <div class="image-wrapper">
                <img src="../assets/landing/easy.png" alt="Image 3">
                <h3><p class="broadheader">Quick and Easy</p></h3>
                <p class="broad">We would like to help you as fast as we can, in doing so signing up is very easy. </p>
            </div>
            
        </div>
        <header class="header">
            <h3>Let's get you started...</h3>
        </header>
    <div class="container">
      <div id="timeline">
        <ol>
          <li class="active">
            <span class="point"></span>
            <h3 class="diplome"><b>Step 1</b></h3>
            <p class="timestamp"><strong>APPLY</strong><br><br>Create your profile and connect your social platforms</p>
          </li>
          <li class="active">
            <span class="point"></span>
            <h3 class="diplome"><b>Step 2</b></h3>
            <p class="timestamp"><strong>BROWSE THROUGH ALL BRANDS</strong><br><br>Browse through many brands and see which brands stand out to you</p>
          </li>
          <li class="active">
            <span class="point"></span>
            <h3 class="diplome"><b>Step 3</b></h3>
            <p class="timestamp"><strong>REQUEST A VOUCHER</strong><br><br>Enter the information required to request a voucher and wait for an approval by the brand</p>
          </li>
          <li class="active">
            <span class="point"></span>
            <h3 class="diplome"><b>Step 4</b></h3>
            <p class="timestamp"><strong>START PROMOTING</strong><br><br>Share and promote your brand new product across your social media platforms</p>
          </li>
        </ol>
      </div>
    </div>
    <div style="margin-top: 100px;"></div>
            <header class="header" >
            <h3>Testimonials</h3>
            </header>
        <div class="testimonial-container">
        <div class="testimonial-card">
            <div class="text">Blockchain's authentication features protect my identity and reputation. They significantly reduce the risk of impersonation, which is crucial for a consistent and trustworthy online presence<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP1.jpg" alt="Image 1"></div>
            <h3 class='person'>Paul</h3></div>
        </div>
        <div class="testimonial-card">
            <div class="text">Decentralization is liberating. I'm not reliant on a single platform or intermediary. Blockchain gives me more freedom and flexibility in my work<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP2.jpg" alt="Image 1"></div>
            <h3 class='person'>Elizabeth</h3></div>
        </div>
        <div class="testimonial-card">
            <div class="text">I can build and protect my reputation on the blockchain. My track record of successful collaborations and ethical practices is recorded and remains unchangeable, instilling trust among brands and my followers<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP3.jpg" alt="Image 1"></div>
            <h3 class='person'>Mary</h3></div>
        </div>
        <div class="testimonial-card">
            <div class="text">With smart contracts, the whole voucher redemption process becomes a breeze. They automate everything, especially ensuring that I get my products on time.<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP4.jpg" alt="Image 1"></div>
            <h3 class='person'>Susan</h3></div>
        </div>
        </div>    
    </div>
</template>
  
<script>
import { useRouter } from 'vue-router'

// import on mount
import { onMounted } from 'vue'

// api
import { API_ENDPOINT, OTHER_CONST } from '@/config/constants.js';

// import axios
import axios from 'axios'


export default {
    setup() {
        const router = useRouter();

        // call api on mount
        onMounted(() => {
            axios.get(API_ENDPOINT)
                .then(res => {
                    console.log(res.data)
                })
                .catch(err => {
                    console.log(err)
                })
        })



        const displayLoginPage = () => {
            router.push('/login')
        }

        return { displayLoginPage }

        
    }
};
</script>

  
<style scoped lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Cormorant+Garamond&family=Eczar&family=Gentium+Plus&family=Libre+Baskerville&family=Libre+Franklin&family=Proza+Libre&family=Rubik&family=Taviraj&family=Trirong&family=Work+Sans&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');



.landing-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100vw;
    font-family: 'Poppins', sans-serif;
    padding-top: 20px;
    background-image:url(../assets/color-bars.svg);
    background-size:cover;
    background-position: center;
    backdrop-filter: blur(0px);
    background-color: #001f3f;
}

.landing-page::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 1, 63, 0.85); /* Adjust the opacity as needed */
    z-index: -1; /* Ensure the semi-transparent pane is behind other elements */
}


.nav-bar ul {
    padding-top: 20px 0;
    margin-bottom: 50px;
    display: flex;
    justify-content: space-evenly;
    flex-direction: row;
    align-items: center;
    gap: 2.5rem;
    

}

.nav-bar li {
    list-style: none;
    display: inline-block;
    
}
.nav-bar ul li a:hover {
        color: red; /* Set the color you want the links to change to on hover */
    }



.content-wrapper {
    display: flex;
    width: 80%;
    max-width: 100vw;
    justify-content: center;
    flex-wrap: wrap;
    margin-bottom: 30px;
}

.text-section {
    width: 50%;
    display: flex;
    flex-direction: column;
    gap: 20px;
}
.intro {
    color: white;
    line-height: 1.5;
    font-size: 1.4em;
    font-family: 'Poppins', serif;
}
.broad {
    color: white;
    line-height: 1.5;
    font-size: 1.2em;
    font-family: 'Poppins', serif;
}
.broadheader {
    color: #E040FB;
    line-height: 1.5;
    font-size: 1em;
    font-family: 'Poppins', serif;
}
h1 {
    font-size: 3.5em;
    color: #E040FB;
    margin-bottom: 20px;
    font-family: 'Poppins';
}

h3 {
    
    font-size: 1.25em;
    font-family: 'Poppins', serif;
    color: white;
}

p {
    color: #E040FB;
    line-height: 1.5;
    font-size: 1.4em;
    font-family: 'Gentium Plus', serif;
    
}

.buttons {
    display: flex;
    gap: 20px;
    font-family: 'Lora', serif;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
    font-family: 'Lora', serif;
}

.sign-in-btn {
    background-color: #E040FB;
    color: #fff;
    font-family: 'Poppins', serif;
}

.apply-btn {
    background-color: #ccc;
    color: #001f3f;
    font-family: 'Poppins', serif;
}

button:hover {
    opacity: 0.8;
    
  
}

.image-section img {
    width: 30rem;
    height: 30rem;
    /* height: auto; */
    display: block;
}


@media (max-width: 768px) {

    .text-section,
    .image-section {
        width: 100%;
        text-align: center;
        /* display: none; */
    }

    .content-wrapper {
        justify-content: center;
    }

    h1 {
        font-size: 2em;
    }

    .buttons {
        justify-content: center;
    }
}
.header h2 {
    max-width: 100%;
    box-sizing: border-box;
    padding: 5px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 40px;
    color: #E040FB;
    font-family: 'Poppins' ;
}
.image-container {
    display: flex;
    justify-content: space-between;
    gap: 20px; 
    align-items: flex-start;
    flex-wrap: wrap; 
    margin-top: 5px; 
    margin-inline: 40px;
}

.image-wrapper {
    text-align: center;
    width: 30%;
    height: 30%; 
    margin-bottom: 20px;
}

.image-wrapper img {
    width: 8em; 
    height: 8em;
}

.image-wrapper p {
    margin: 5px; 
}
.header h3 {
    max-width: 100%;
    box-sizing: border-box;
    padding: 5px;
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 40px;
    font-family: 'Poppins';
    color: #E040FB;
}
.container {
  display: flex; 
  padding: 20px; 
  margin-bottom: 30px;
}

#timeline ol {
  position: relative;
  display: block;
  margin-top: 20px;
  margin-bottom: 100px;
  height: 1px;
  padding-inline-start: 0;
}

#timeline li {
  position: relative;
  float: left;
  width: calc(25%); 
  height: 1px;
  background: transparent; 
  color: #999;  
}

#timeline li .diplome {
  text-align: center;
  margin-top: 20px;
  margin-bottom: 10px; 
  font-size: 1.2em;
  font-family: 'Poppins',sans-serif serif;
  color: #E040FB;
}

#timeline li .point {
  content: "";
  display: block;
  width: 20px; 
  height: 20px; 
  border-radius: 50%;
  border: 1px solid #0b2c5c; 
  background: #fff;
  position: absolute;
  top: -5px; 
  left: calc(50% - 5px); 
}

#timeline li .timestamp {
  font-size: 1.2em;
  font-family: 'Poppins',sans-serif;
  text-align: center;
  margin-right: 20px;
  margin-bottom: 20px;
}

#timeline li.active .point {
  border: 5px solid #0b2c5c; 
}
#timeline li.active .timestamp {
  color: white; 
}
  .testimonial-container{
    margin-top: 20px;
    margin-bottom: 20px;
    margin-left: 20px;
    height: auto;
    display: flex;
    flex-direction: row;
    gap: 20px;
    justify-content:center; 
    
    align-items: center;
    flex-wrap: wrap; 
  }
  .testimonial-card{
    height: 300px;
    width: 200px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 8px 30px rgba(0,0,0,.3);
    display: flex;
    flex-flow: column;
    justify-content:space-between;
    margin-right: 35px;
  }
  .text{
    padding: 2.2em;
    line-height: 1.7em;
    position: relative;
    font-size: 12px;
    color: black; 
  }
  .image{
    height: 75px;
    width: 75px;
    border-radius: 50%;
    border: 5px solid white;
    position: absolute;
    top: -50%;
    left: 50%;
    transform: translateX(-50%);
    display:flex;
    justify-content:center;
    overflow: hidden;
  }
  .image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
  }
  .footer{
    background: linear-gradient(90deg, rgba(125, 187, 238, 0) 0%, rgb(18, 96, 141) 75%);
    height: 90px;
    border-radius: 0 0 10px 10px;
    position:relative; 
  }
  .quote{
    font-size: 180%;
    float: right;
    opacity: .1;
    transform: rotate(10deg) translate(-10px, -40px);
    color:#4D3FA3;
  }
  .person{
    color:white;
    position: absolute;
    top: 60%;
    left: 50%;
    transform: translate(-50%, -100%);
    font-size: 0.7rem;
    font-weight: 600;
    text-align: center;
  }
</style>
  