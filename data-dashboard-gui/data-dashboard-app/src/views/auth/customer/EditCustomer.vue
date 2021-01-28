<template>
  <div class="newcustomer">

    <div class="card newustomer">
      <div class="card-header">

        <b>New Kunde</b>
        <button type="button" class="btn" v-on:click="reloadData"><img src="@/assets/images/refresh.svg" width="20" /></button>
        <router-link class="navbar-brand" to="/customerlist"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
      </div>
      <div class="card-body">
        <form id="newustomerform" @submit.prevent="handleSaveData">
          <div class="form-group">
            <label for="nameinput" class="item-title">Name</label>
            <input class="form-control" id="nameinput" placeholder="" name="name" v-model="customer.name">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Kunde-Identität</label>
            <input class="form-control" id="keyinput" placeholder="" name="customerKey" v-model="customer.customerKey">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Kunde-Status</label>

            <div class="form-check form-check-inline" v-for="item in customerStatusList" :key="item">
              <input class="form-check-input" type="radio" name="status" v-bind:id="'rd' + item" v-model="customer.status" v-bind:value="item">
              <label class="form-check-label" v-bind:for="'rd' + item">
                {{item}}
              </label>
            </div>


          </div>
          <div class="form-group">
            <div class="card">
              <div class="card-body">
                Ordner-Liste
              </div>
              <div class="card-body" v-if="false">
                <div class="form-group" v-for="item in customer.folders" :key="item.id">
                  <label class="item-title2" v-bind:for="'folder' + item.folderType">{{item.folderType}}</label>
                  <input class="form-control folder" name="folder" v-bind:id="'folder' + item.folderType" v-model="item.folderPath">

                </div>
              </div>
              <div class="card-body">
                <div class="form-group" v-for="item in folderTypes" :key="item.id">
                  <label class="item-title2" v-bind:for="'folder' + item.value">{{item.title}}</label>
                  <input class="form-control folder" name="folder" v-bind:id="'folder' + item.value" v-bind:value="getFolderPath(item.value)" v-on:input="setFolderPath( item.value, $event.target.value)" >

                </div>
              </div>
            </div>
          </div>


          <div class="form-group">
            <span v-if="message != ''" class="alert alert-danger" style="float:left; max-width: calc(100% - 150px);" v-html="message"></span>
            <button type="submit" class="btn btn-primary" style="float:right;">Erstellen</button>
          </div>
        </form>

      </div>
    </div>
    <div style="border:1px solid gray; margin 10px;">
      {{customer}}
    </div>
  </div>
</template>

<style>

.card.newustomer{
  width: 80%;
  margin: 10px auto;
}

input.form-control {
    display: inline-block;
    width: calc(100% - 200px);
}

input.form-control.folder{
    display: inline-block;
    width: calc(100% - 280px);
}

input.form-control {
    display: inline-block;
    width: calc(100% - 200px);
}

label.item-title{
  width: 170px;
}

label.item-title2{
  width: 270px;
}

</style>
<script>
import { customersService } from '../../../services/customers.service';
import router from '../../../router'

export default {
    name: 'NewCustomer',
    data () {
        return {
          customer: {},
          folderTypes:[],
          customerStatusList:[],
          message: "",
          id: false
        }
    },
    computed: {

    },
    methods: {
      loadInitData(){

          customersService.getNew().then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          this.customer = data.customer;
          this.folderTypes = data.foldertypes;
          this.customerStatusList = data.customerstatus;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      loadCustomerData(){

          customersService.getCustomer(this.id).then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          this.customer = data.customer;
          this.folderTypes = data.foldertypes;
          this.customerStatusList = data.customerstatus;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      handleSaveData () {

        var func = false;
        if(this.isEdit()){
          func = customersService.update(this.customer)
        }
        else{
          func = customersService.createNew(this.customer)
        }

        this.submitted = true;
        func.then(async response => {
          const data = await response.json();

          if (!response.ok) {
            var error = (data && data.message) || response.statusText;

            this.message = data.message;
            if(data._embedded && data._embedded.errors){
              for(var i in data._embedded.errors){
                this.message += "<br>" + data._embedded.errors[i].message;
              }

            }
            return Promise.reject(error);
          }

          this.customer = data;
          router.push('/customerlist');

        })
      },
      isNew(){
        return this.id == false;
      },
      isEdit(){
        return this.id;
      },
      reloadData(){
        if(this.isEdit()){
          this.loadCustomerData();
        }
        else{
          this.loadInitData()
        }
      },
      getFolderPath(type){
        for(var folderIndex in this.customer.folders){
          if(this.customer.folders[folderIndex].folderType == type){
            return this.customer.folders[folderIndex].folderPath;
          }
        }
        return "";
      },
      setFolderPath(type, value){
        for(var folderIndex in this.customer.folders){
          if(this.customer.folders[folderIndex].folderType == type){
            this.customer.folders[folderIndex].folderPath = value;
            return;
          }
        }

      }

    },
    created () {
      if("id" in this.$route.params){
        this.id = this.$route.params.id;
        console.log('route', this.$route.params.id);
      }

      this.reloadData()

    }
};
</script>

