const BASE_URL_DEV = 'http://localhost';
const BASE_URL_PROD = '';

const ENV_API : string = 'prod';

const getApiUrl = () =>{
    switch(ENV_API){
        case 'dev': return BASE_URL_DEV;
        case 'prod': return BASE_URL_PROD;
        default: return BASE_URL_DEV;
    }
}

export const API = getApiUrl()
