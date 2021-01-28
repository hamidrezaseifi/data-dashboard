
<template>
    <div class="card connection-view" style="">

        <div class="card-header">
            <b>Kunden-Liste</b>
            <button type="button" class="btn" v-on:click="loadConnections"><img src="@/assets/images/refresh.svg" width="20" /></button>
            <router-link class="" to="/data/settings/connections/new"><img src="@/assets/images/plus-circle.svg" width="20" /></router-link>
            <router-link v-if="inBox" class="" to="/data/settings/connections"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
        </div>
        <div class="card-body">

            <table id="connectionstable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="th-sm clm-name connectionlist">Name</th>
                    <th class="th-sm clm-server connectionlist">Server</th>
                    <th class="th-sm clm-db connectionlist">Datenbank</th>
                    <th class="th-sm clm-type connectionlist">Datenbank-Typ</th>
                    <th class="th-sm clm-type connectionlist">Status</th>
                    <th class="th-sm clm-action connectionlist">...</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in connectionsList" :key="item.id">
                    <td class="th-sm clm-name connectionlist">{{item.name}}</td>
                    <td class="th-sm clm-server connectionlist">{{item.server}}</td>
                    <td class="th-sm clm-clm-db connectionlist">{{item.databaseName}}</td>
                    <td class="th-sm clm-type connectionlist">{{item.databaseType}}</td>
                    <td class="th-sm clm-type connectionlist">{{item.status}}</td>
                    <td class="th-sm clm-action connectionlist">
                        <router-link class="" :to="'/data/settings/connections/edit/' + item.id" title="Bearbeiten">
                            <img src="@/assets/images/card-list.svg" width="20" />
                        </router-link>
                        <router-link class="" :to="'/data/settings/connections/clone/' + item.id" title="Verdopplen">
                            <img src="@/assets/images/clone.png" width="20" />
                        </router-link>
                    </td>
                </tr>

                </tbody>
            </table>

        </div>

    </div>
</template>

<script>

import { dataSettingsService } from '../../../../services/datasettings.service';

export default {
  components: {
  },
  props:["inBox"],
  data() {
    return {
      connectionsList:[],
    }
  },
  methods: {
    loadConnections(){
      dataSettingsService.getAllConnections().then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          this.connectionsList = data;

          }).catch(error => {
            console.error("There was an error!", error);
      })
    },
    createConnection(){

    }
  },
  created () {

      this.loadConnections()
  }
}
</script>

<style>

.card.connection-view {
    width: calc(100% - 200px);
    margin: 40px 100px;
}

.clm-server.connectionlist{
    width: 220px;
}

.clm-db.connectionlist{
    width: 250px;
}

.clm-type.connectionlist{
    width: 200px;
}

.clm-status.connectionlist{
    width: 200px;
}

.clm-action.connectionlist{
    width: 90px;
    max-width: 90px;
}

.Krankenhaus-view{
    margin: 40px 40px;
}

.Krankenhaus-toolbar{
    margin: 10px 0;
    background: #efefef;
    padding: 10px 10px;
    border: 1px solid gray;
    border-radius: 5px;
}

.chart-container {
    float:left;
    width: 550px;
    height: 400px;
    border: 1px solid gray;
    padding: 5px;
    margin: 5px;
}
</style>
