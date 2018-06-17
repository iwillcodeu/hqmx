<template>
  <div>
    <img id="main" src="../assets/home.png"/>
    <div id="menu">
      <img id="Account" src="../assets/account.png"/>
      <img id="MyBet" src="../assets/mybet.png"/>
      <img id="JoinBet" src="../assets/joinbet.png"/>
      <img id="CreateBet" src="../assets/createbet.png" @click="displayHotGame"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "HomePage",
    methods: {
      displayHotGame() {
        this.$router.push({
          name: "HotGame"
        })
      }
    }
  }
</script>

<style scoped>

  #main {
    margin-top: 0px;
    width: 100%;
    height: 600px;
  }

  #menu {
    background: #000000;
  }

  #Account {
    width: 120px;
    height: 120px;
    margin-right: 40px;
  }

  #MyBet {
    width: 120px;
    height: 120px;
    margin-right: 40px;
  }

  #JoinBet {
    width: 120px;
    height: 120px;
    margin-right: 40px;
  }

  #CreateBet {
    width: 120px;
    height: 120px;
  }
</style>
