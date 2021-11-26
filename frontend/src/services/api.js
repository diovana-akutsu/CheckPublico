import axios from "axios";

const api = axios.create(
    {
        baseURL: "http://52.73.218.16/",
        headers: {
            'Content-Type': 'application/json'
        }
    }

);


export default api;

