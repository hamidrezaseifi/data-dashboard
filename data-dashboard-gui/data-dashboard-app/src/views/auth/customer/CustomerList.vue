<template>

  <div class="card panel customerlistpage">
    <div class="card-header">
      <b>Kunden-Liste</b>
      <button type="button" class="btn" v-on:click="loadCustomers"><img src="@/assets/images/refresh.svg" width="20" /></button>
      <button type="button" class="btn" v-on:click="createNew"><img src="@/assets/images/plus-circle.svg" width="20" /></button>
      <router-link v-if="inBox" class="navbar-brand" to="./customerlist"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
    </div>
    <div class="card-body">

      <table id="customerlisttable" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
          <th class="th-sm clm-name customerlist">Name</th>
          <th class="th-sm clm-key customerlist">Key</th>
          <th class="th-sm clm-status customerlist">Status</th>
          <th class="th-sm clm-status customerlist">...</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in customers" :key="item.id">
          <td class="th-sm clm-name customerlist">{{item.name}}</td>
          <td class="th-sm clm-key customerlist">{{item.customerKey}}</td>
          <td class="th-sm clm-status customerlist">{{item.status}}</td>
          <td class="th-sm clm-status customerlist">
            <router-link class="navbar-brand" :to="'./customer/edit/' + item.id"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
          </td>
        </tr>

        </tbody>
      </table>

    </div>
  </div>

</template>

<style>

  #customerlisttable{
    font-size: 14px;
  }

  #customerlisttable tbody tr td{
    font-size: 14px;
    font-weight: 200;
  }

  .card.panel.customerlistpage{
    width: calc(100% - 100px);
    margin: 20px auto 10px auto;

    display: block;
    float: none;
  }

  .clm-key.customerlist{
      max-width: 150px;
      width: 150px;
  }

  .clm-status.customerlist{
      max-width: 80px;
      width: 80px;
  }

</style>
<script>
import { customersService } from '../../../services/customers.service';
import router from '../../../router'

export default {
    name: 'CustomerList',
    data () {
        return {
          customers: {}
        }
    },
     props: ['inBox'],
    computed: {

    },
    methods: {
      loadCustomers(){
          customersService.getAll().then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          this.customers = data;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      createNew(){router.push("/newcustomer")}
    },
    created () {

      this.loadCustomers()
    }
};
</script>

