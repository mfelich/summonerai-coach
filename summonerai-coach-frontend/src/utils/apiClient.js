import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080", // backend root url
  headers: {
    "Content-Type": "application/json",
  },
});

export default apiClient;