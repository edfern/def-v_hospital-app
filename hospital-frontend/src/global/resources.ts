const BASE_URL_DEV = 'http://localhost:8485';
const BASE_URL_PROD = 'https://www.def-v.com:8443/hospital-v1.0-SNAPSHOT';

const ENV_API : string = 'prod';

const getApiUrl = () =>{
    switch(ENV_API){
        case 'dev': return BASE_URL_DEV;
        case 'prod': return BASE_URL_PROD;
        default: return BASE_URL_DEV;
    }
}

export const API = getApiUrl()
