<template>
    <div class="card panel workbooklistpage">
        <div class="card-header">
            <b>Workbook-Liste</b>
            <button type="button" class="btn" v-on:click="loadWorkbooks"><img src="@/assets/images/refresh.svg" width="20" /></button>
            <router-link class="" to="/data/settings/workbook/new"><img src="@/assets/images/plus-circle.svg" width="20" /></router-link>
            <router-link v-if="inBox" class="navbar-brand" to="/data/settings/workbook/list"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
        </div>
        <div class="card-body">

            <table id="customerlisttable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="th-sm clm-name workbooklist">Name</th>
                    <th class="th-sm clm-key workbooklist">Kunde</th>
                    <th class="th-sm clm-status workbooklist">Status</th>
                    <th class="th-sm clm-action workbooklist">...</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in workbooks" :key="item.id">
                    <td class="th-sm clm-name workbooklist">{{item.name}}</td>
                    <td class="th-sm clm-customer workbooklist">{{item.customerName}}</td>
                    <td class="th-sm clm-status workbooklist">{{item.status}}</td>
                    <td class="th-sm clm-action workbooklist">
                        <router-link class="" :to="'/data/settings/workbook/edit/' + item.id"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
                    </td>
                </tr>

                </tbody>
            </table>

        </div>
    </div>

 </template>

<style>

.card.panel.workbooklistpage{
    width: calc(100% - 100px);
    margin: 20px auto 10px auto;

    display: block;
    float: none;
}

.clm-customer.workbooklist{
    width: 190px;
    max-width: 190px;
}

.clm-status.workbooklist{
    width: 120px;
    max-width: 120px;
}

.clm-action.workbooklist{
    width: 120px;
    max-width: 120px;
}

</style>
<script>
import { workbookService } from '../../../../services/workbook.service';

export default {
    name: 'WorkflowList',
    data () {
        return {
          connections:[],
          errmessage: "",
          okmessage: "",
          id: false,
          isSelectSourceDialogVisible: false,
          currentUpdateDateTime: new Date(),
          customers: [],
          workbooks: []
        }
    },
    components:{
    },
    props:["inBox"],
    computed: {
      customerId: {
        get () {

          return this.$store.state.authentication.loginData.customer.id
        }

      },
      pageTitle: function (){
        return this.isNew() ? "Neu Workbook" : "Workbook " + this.workbook.name + " bearbeiten"
      },

    },
    methods: {
      loadWorkbooks(){
        workbookService.getAllWorkbooks(this.customerId).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.message) || response.statusText;
              return Promise.reject(error);
            }

            this.customers = data.customers;
            this.workbooks = data.workbooks
            this.currentUpdateDateTime = new Date()
            console.log("workbooks", data)
            }).catch(error => {
              console.error("There was an error!", error);
            })
      }

    },
    created () {
        this.loadWorkbooks()

    }
};
</script>

