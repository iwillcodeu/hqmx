<template>
  <div>
    <header>
      <img id="logo" src="../assets/worldcup.jpg"/>
      <span style="width:100%; text-align:left; padding-top: 10px; display:block;">区块链去中心化竞猜平台</span>
    </header>
    <h1 id="module">创建竞猜</h1>
    <hr/>
    <div>
      <b-table striped hover :items="items"></b-table>
    </div>
  </div>
</template>

<script>
  export default {
    name: "HotGame",
    data() {
      return {
        items: [
          {时间: "2018-7-2 18:00", 参赛队伍: '阿根廷VS巴西', 赛事: '世界杯'}
        ]
      }
    }
  }
</script>

<style scoped>
  header {
    width: 100%;
    height: 40px;
    background-color: #000;
    color: #fff;
  }

  #logo {
    width: 40px;
    height: 40px;
    float: left;
    margin-left: 35px;
    margin-right: 50px;
  }

  #module {
    padding-top: 20px;
    font-size: 15px;
    color: orange;
    text-align: left;
    padding-left: 40px;
  }

  hr {
    margin: 0px 40px 20px 40px;
  }
</style>
