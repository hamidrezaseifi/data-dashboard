<template>
  <div>

    <CustomersList in-box="true" class="from-dashboard" />

  </div>
</template>

<style>

  .card.panel.tasklist.from-dashboard, .card.panel.customerlistpage.from-dashboard{
    width: 700px;
    margin: 10px;
    float: left;
    display: inline-block;

  }

  .card.panel{
    margin: 10px;
    float: left;
    display: inline-block;
    height: 350px;
    max-height: 350px;
  }

  .card.panel .card-body{
    height: calc(100% - 65px);
    overflow-y: auto;
  }

  .card.panel .card-body{
   padding: 10px

  }

</style>
<script>

import CustomersList from './customer/CustomerList.vue'

export default {

    data () {
        return {

        }
    },
    components: {
      CustomersList
    },
    computed: {
        user () {
            return this.$store.state.authentication.user;
        }
    },
    methods: {

    },
    created () {


    }
};
</script>