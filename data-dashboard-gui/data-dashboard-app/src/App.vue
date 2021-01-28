<template>
  <div id="app">
    <TopBar />
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.clear{
  clear: both;
}
</style>

<script>

import TopBar from './components/TopBar.vue'

export default {
    name: 'app',
    data () {
        return {

        }
    },
    components: {
      TopBar
    },
    computed: {
        alert () {
            return this.$store.state.alert
        },
        isLoggedIn: {
            get () {
              return this.$store.state.authentication.status.loggedIn
            }
        }
    },
    watch:{
        $route (){
            // clear alert on location change
            this.$store.dispatch('alert/clear');
        }
    }
};
</script>
