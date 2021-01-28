//import config from 'config';
import { createRequestOptions } from '../helpers';

export const customersService = {

    getAll,
    getCustomer,
    getNew,
    createNew,
    update
};

function getAll() {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_customers_baseUrl + "/all", requestOptions);
}

function getCustomer(id) {

    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_customers_baseUrl + "/initedit/" + id, requestOptions);
}

function getNew() {

    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_customers_baseUrl + "/new", requestOptions);
}

function createNew(customer) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(customer));

    return fetch(process.env.VUE_APP_customers_baseUrl + "/create", requestOptions);
}

function update(customer) {
    const requestOptions = createRequestOptions('POST', true, JSON.stringify(customer));

    return fetch(process.env.VUE_APP_customers_baseUrl + "/update", requestOptions);
}

