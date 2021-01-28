
export function authHeader() {
    // return authorization header with jwt token
    let loginData = JSON.parse(sessionStorage.getItem('login-data'));

    var headers = {'Content-Type': 'application/json'};

    if (loginData && loginData.accessToken) {
        headers['Authorization'] =  'Bearer ' + loginData.accessToken;
    }

    return headers;
}

export function createRequestOptions(requestMethod, useCredentials, requestBody) {
    // return authorization header with jwt token
    const requestOptions = {
            method: requestMethod,
            //withCredentials: true,
            //credentials: 'include',
            credentials: 'include',
            headers: authHeader()
    };

    if(useCredentials){
        requestOptions['credentials'] = 'include';
    }
    else{
        requestOptions['credentials'] = 'include';
    }

    if(requestBody && requestMethod == "POST"){
        requestOptions['body'] = requestBody;
    }



    return requestOptions;
}
