<template>
  <nav class="navbar">
    <img class="logo" src="../../assets/project_logo.png" alt="logo" />
    <!-- <div class="page">Brand Dashboard</div> -->
    <div class="profile-section">
      <div class="username">{{ username }}</div>
      <button @click="handleLogout" class="logout-btn">Logout</button>
    </div>
  </nav>
</template>

<script>
// vue-3 router
import { useRouter } from "vue-router";

// pinia
import { useUserStore } from "@/store/user";

export default {
  props: {
    username: {
      type: String,
      required: true,
    },
  },
  setup() {
    const router = useRouter();
    const userStore = useUserStore();
    const handleLogout = () => {
      // Clear the user details from the store
      // You can also clear the token from localStorage
      // But it's not required as the token will be removed
      // once the user logs out
      userStore.clearUserDetails();
      router.push("/login");
    };
    return {
      handleLogout,
    };
  },
};
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #001f3f;
  color: #f7f7f7;
  font-family: "Poppins", sans-serif;
  position: relative;
  z-index: 1000;
  /* white outline */
  outline: 1px solid white;
}

.logo {
  height: 3em;
}

.profile-section {
  display: flex;
  align-items: center;
  gap: 20px;
}

.page {
  font-size: 1.2em;
  padding-left: 8em;
}

.profile-pic img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
}

.logout-btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #e040fb;
  font-size: 0.9em;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.logout-btn:hover {
  opacity: 0.8;
}

/* Responsive Styles */
@media (max-width: 768px) {
  .navbar {
    padding: 10px;
  }

  .logo {
    width: 10px;
    height: 5px;
  }

  .profile-section {
    gap: 10px;
  }

  .logout-btn {
    padding: 5px 10px;
  }
}
</style>
