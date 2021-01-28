<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light m-navbar">
    <router-link class="navbar-brand" to="/">Data Portal ...</router-link>

    <div v-if="isLoggedIn" class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav menu-nvabar">
        <li class="nav-item" v-bind:class="{ active: isActive('/') }"><router-link class="nav-link" to="/">Dashboard</router-link></li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownData" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Daten Einstellungen
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownData">
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/data/testchart') }" to="/data/testchart">TestChart</router-link >
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/data/krankenhaus') }" to="/data/krankenhaus">KrankenhausChart</router-link>
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/data/settings/connections') }" to="/data/settings/connections">Datenquelle-Verbindung</router-link>

          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdowncustomer" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Kunde
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdowncustomer">
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/customerlist') }" to="/customerlist">Kunde-Liste</router-link >
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/newcustomer') }" to="/newcustomer">neue Kunde</router-link>

          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdowntask" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Aufgaben
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdowntask">
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/tasklist') }" to="/tasklist">Aufgabe-Liste</router-link >
            <router-link class="dropdown-item" v-bind:class="{ active: isActive('/newtask') }" to="/newtask">neue Aufgabe</router-link>
            <div class="dropdown-divider"></div>
            <router-link class="dropdown-item" to="/">Item 3</router-link>
          </div>
        </li>
        <li class="nav-item" v-bind:class="{ active: isActive('/about') }"><router-link class="nav-link" to="/about">About</router-link></li>
      </ul>


      <ul class="navbar-nav user-box">
        <li class="nav-item dropdown">
          <a class="nav-link" href="#" id="userNavbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>{{user.displayName}}</span>
            <i class="arrow down"></i>
          </a>
          <div class="dropdown-menu" aria-labelledby="userNavbarDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" @click="logout">Logout</a>

          </div>
        </li>
      </ul>
    </div>
  </nav>

</template>

<style>
.m-navbar{
  height: 70px;
}
  .navbar-brand{
    margin-right: 30px;
    width: 300px;
  }

  .user-box{
    width: 220px;
    border: 1px solid gray;
    display: inline-block;
    padding: 5px;
    -webkit-box-shadow: 3px 3px rgba(0,0,0,0.3);
    box-shadow: 3px 3px rgba(0,0,0,0.3);
  }

  .user-box span{
    display: inline-block;
  }

  .user-box li.nav-item{
    width: 100%;
  }

  .user-box li.nav-item a.nav-link {
    width: 100%;
  }

  .user-box li.nav-item a.nav-link .arrow {
    float: right;
    margin-top: 5px;
  }

  #navbarSupportedContent{
    width: calc(100% - 300px);
  }
  ul.navbar-nav.menu-nvabar {
      width: calc(100% - 250px);

  }

  ul.navbar-nav user-box{
    width: 230px;
  }


  .arrow {
    border: solid black;
    border-width: 0 3px 3px 0;
    display: inline-block;
    padding: 3px;
  }

  .down {
    transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
  }

</style>
<script>

export default {
    name: 'TopBar',
    data () {
        return {

        }
    },
    computed: {
        alert () {
            return this.$store.state.alert
        },
        isLoggedIn: {
            get () {
              return this.$store.state.authentication.status.loggedIn
            }
        },
        user: {
            get () {

              return this.$store.state.authentication.loginData.user
            }

        },
        currentRouteName() {
          return this.$route.name;
        }
    },
    methods: {
        logout () {

            const { dispatch } = this.$store;
            dispatch('authentication/logout');

        },
        isActive(url){
          return this.$route.url == url
        }
    }
};
</script>

