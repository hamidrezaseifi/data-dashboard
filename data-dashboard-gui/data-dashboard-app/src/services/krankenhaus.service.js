//import config from 'config';
import { createRequestOptions } from '../helpers';

export const krankenhausService = {

    getAll,
    getFachabteilung,
    getErkrankung,
    getBehandlung
};

function getAll() {
    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_charts_baseUrl + "/krankenhaus", requestOptions);
}

function getFachabteilung(kh_key) {

    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_charts_baseUrl + "/krankenhaus/fachabteilung/" + kh_key, requestOptions);
}

function getErkrankung(kh_key) {

    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_charts_baseUrl + "/krankenhaus/erkrankung/" + kh_key, requestOptions);
}

function getBehandlung(kh_key) {

    const requestOptions = createRequestOptions('GET', true);

    return fetch(process.env.VUE_APP_charts_baseUrl + "/krankenhaus/behandlung/" + kh_key, requestOptions);
}
