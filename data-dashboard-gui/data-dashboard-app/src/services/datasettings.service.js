//import config from 'config';
import { createRequestOptions } from '../helpers';

export const dataSettingsService = {

    getAllConnections,
    readConnection,
    newConnection,
    createConnection,
    updateConnection,
    deleteConnection,
    testConnection,
    cloneConnection,
    readConnectionTables,
    readTableColumns,
    readQueryColumns
};

function getAllConnections() {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/readall", requestOptions);
}

function readConnection(id) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/read/" + id, requestOptions);
}

function newConnection() {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/new", requestOptions);
}

function createConnection(connection) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(connection));

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/create", requestOptions);
}

function updateConnection(connection) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(connection));

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/update", requestOptions);
}

function deleteConnection(connection) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(connection));

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/delete", requestOptions);
}

function testConnection(connection) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(connection));

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/testconnection", requestOptions);
}

function cloneConnection(id) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/clone/" + id, requestOptions);
}

function readConnectionTables(id) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/tablelist/" + id, requestOptions);
}

function readTableColumns(connectionId, tableName) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/tablecolumnlist/" + connectionId + "/" + tableName, requestOptions);
}

function readQueryColumns(request) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(request));

    return fetch(process.env.VUE_APP_datasettings_baseUrl + "/connections/querycolumnlist", requestOptions);
}
