<template>
  <div class="newconnection">

    <div class="card newconnection">
      <div class="card-header">

        <b>New Verbindung</b>
        <button type="button" class="btn" v-on:click="reloadData"><img src="@/assets/images/refresh.svg" width="20" /></button>
        <router-link class="" to="/data/settings/connections"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
      </div>
      <div class="card-body">
        <form id="newconnectionform" @submit.prevent="handleSaveData">
          <div class="form-group">
            <label for="nameinput" class="item-title">Name</label>
            <input class="form-control" id="nameinput" placeholder="" name="name" v-model="connection.name">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Server</label>
            <input class="form-control" id="keyinput" placeholder="" name="server" v-model="connection.server">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Port</label>
            <input class="form-control" id="portinput" placeholder="" name="port" v-model="connection.port">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Datenbank</label>
            <input class="form-control" id="keydatabaseName" placeholder="" name="databaseName" v-model="connection.databaseName">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Datenbank-Typ</label>
            <select class="form-control" id="keydatabaseTypelist" v-model="connection.databaseType" name="databaseType">
              <option v-for="item in databaseTypeList" :key="item" v-bind:value="item" >{{item}}</option>
            </select>
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Benutzname</label>
            <input class="form-control" id="keyusername" placeholder="" name="dbusername" v-model="connection.username">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Kennwort</label>
            <input class="form-control" type="password" id="keypassword" placeholder="" name="dbpassword" v-model="connection.password">
          </div>
          <div class="form-group">
            <label for="keyinput" class="item-title">Status</label>

            <div class="form-check form-check-inline" v-for="item in connectionStatusList" :key="item">
              <input class="form-check-input" type="radio" name="status" v-bind:id="'rd' + item" v-model="connection.status" v-bind:value="item">
              <label class="form-check-label" v-bind:for="'rd' + item">
                {{item}}
              </label>
            </div>

          </div>

          <div class="form-group">
            <span v-if="errmessage != ''" class="alert alert-danger" style="float:left; max-width: calc(100% - 300px);" v-html="errmessage"></span>
            <span v-if="okmessage != ''" class="alert alert-success" style="float:left; max-width: calc(100% - 300px);" v-html="okmessage"></span>

            <button type="button" class="btn btn-secondary actionbutton" v-on:click="testConnection" style="float:right;">Test</button>

            <button type="submit" class="btn btn-primary actionbutton" style="float:right;">{{saveButtonName}}</button>
          </div>
        </form>

      </div>
    </div>
    <div style="border:1px solid gray; margin 10px;">
      {{connection}}
    </div>
  </div>

</template>

<style>

.actionbutton{
  margin-left: 10px;
  width: 120px;
}

.card.newconnection{
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

input.form-control , select.form-control {
    display: inline-block;
    width: calc(100% - 170px);
}

input.form-control[type=password] {
    text-align: left;
    margin: 0;
    padding-left: 12px;
}

label.item-title{
  width: 170px;
}

label.item-title2{
  width: 270px;
}

</style>
<script>
import { dataSettingsService } from '../../../../services/datasettings.service';
import router from '../../../../router'

export default {
    name: 'NewConnection',
    data () {
        return {
          connection: {},
          connectionStatusList:[],
          databaseTypeList:[],
          errmessage: "",
          okmessage: "",
          id: false
        }
    },
    props:["inBox", "clone"],
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      }
    },
    methods: {
      loadInitData(){

          dataSettingsService.newConnection().then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              return Promise.reject(error);
            }

            this.connection = data.connection;
            this.connectionStatusList = data.status;
            this.databaseTypeList = data.dbtype;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      loadConnectionData(){

          dataSettingsService.readConnection(this.id).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              return Promise.reject(error);
            }

            this.connection = data.connection;
            this.connectionStatusList = data.status;
            this.databaseTypeList = data.dbtype;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      loadCloneData(){

          dataSettingsService.cloneConnection(this.id).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              return Promise.reject(error);
            }

            this.connection = data.connection;
            this.connectionStatusList = data.status;
            this.databaseTypeList = data.dbtype;

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      handleSaveData () {

        var func = false;
        if(this.isEdit()){
          func = dataSettingsService.updateConnection(this.connection)
        }
        else{
          func = dataSettingsService.createConnection(this.connection)
        }

        this.submitted = true;
        func.then(async response => {
          const data = await response.json();

          if (!response.ok) {
            var error = (data && data.errmessage) || response.statusText;

            this.errmessage = data.errmessage;
            if(data._embedded && data._embedded.errors){
              for(var i in data._embedded.errors){
                this.errmessage += "<br>" + data._embedded.errors[i].errmessage;
              }

            }
            return Promise.reject(error);
          }

          this.connection = data;
          router.push('/data/settings/connections');

        })
      },
      testConnection(){
          this.okmessage = "";
          this.errmessage = "";
          dataSettingsService.testConnection(this.connection).then(async response => {
            const data = await response.json();
            console.log("Test Connection", data);
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              this.errmessage = data.message;
              return Promise.reject(error);
            }
            this.okmessage = "Die Verbindung war erfolgreich";
          }).catch(error => {
            console.error("There was an error!", error);
          })

      },
      isNew(){
        return this.id == false;
      },
      isEdit(){
        return this.id;
      },
      isClone(){
        return this.clone;
      },
      reloadData(){
        if(this.isEdit()){
          this.loadConnectionData();
        }
        if(this.isNew()){
          this.loadInitData()
        }
        if(this.isClone()){
          this.loadCloneData()
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

