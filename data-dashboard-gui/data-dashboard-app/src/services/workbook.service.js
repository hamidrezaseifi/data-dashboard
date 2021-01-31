//import config from 'config';
import { createRequestOptions } from '../helpers';

export const workbookService = {

    getAllWorkbooks,
    readWorkbook,
    cloneWorkbook,
    newWorkbook,
    createWorkbook,
    updateWorkbook,
    deleteWorkbook
};

function getAllWorkbooks(customerid) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/readall/" + customerid, requestOptions);
}

function readWorkbook(customerid, id) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/read/" + customerid + "/" + id, requestOptions);
}

function cloneWorkbook(customerid, id) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/clone/" + customerid + "/" + id, requestOptions);
}

function newWorkbook(customerid) {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/new/" + customerid, requestOptions);
}

function createWorkbook(customerid, workbook) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(workbook));

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/create/" + customerid, requestOptions);
}

function updateWorkbook(customerid, workbook) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(workbook));

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/update/" + customerid, requestOptions);
}

function deleteWorkbook(customerid, workbook) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(workbook));

    return fetch(process.env.VUE_APP_workbook_baseUrl + "/delete/" + customerid, requestOptions);
}
