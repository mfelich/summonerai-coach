import apiClient from "../utils/apiClient";

export const analyzePlayer = async (requstBody) => {
  const response = await apiClient.post("/ai-analyze", requstBody);
  return response.data;
};
