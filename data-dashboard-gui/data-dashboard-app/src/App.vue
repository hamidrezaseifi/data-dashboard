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

.clm-action a{
  padding: 5px;
}

.modal{

    padding-right: 17px;
    opacity: 1;
    background: #80808085;
}

.modal-header{
  background: #335e9e;
  color: #f7f7e5;
}

.modal-header button.close{
  padding: 10px 10px;
  color: #f7f7e5;
}

.modal-header .modal-title{
  line-height: 14px;
}

.modal-footer{
  padding: 3px 10px !important;
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
    head: {
       link: [
             {
                 rel: "icon",
                 href: require("./assets/images/mq-logo.svg")
             },
          ]
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
    methods: {
      uuidv4(){
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
          var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
          return v.toString(16);
        });
      },
    },
    watch:{
        $route (){
            // clear alert on location change
            this.$store.dispatch('alert/clear');
        }
    }
};
</script>
