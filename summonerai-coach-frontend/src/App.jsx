import "./App.css";
import RequestForm from "./components/ai-analyzer/RequestForm";
import ResponseData from "./components/ai-analyzer/ResposneData";
import ElectricBorder from "./components/react-bits/ElectricBorder";
import logo from "./assets/logo.png";
import { useState } from "react";

function App() {

  const [analysisResult, setAnalysisResult] = useState(null);

  return (
    <div className="w-screen h-screen relative overflow-hidden">
      <div className="absolute inset-0 flex items-center justify-center z-10 overflow-hidden px-4 sm:px-6">
        <div className="w-full max-w-[640px]">
          <ElectricBorder
            color="#FFE066"
            speed={0.4}
            chaos={0.6}
            thickness={10}
            style={{ borderRadius: 14 }}
            className="w-full"
          >
            <div className="bg-secondary rounded-2xl shadow-2xl py-8 px-4 w-full">
            <div className="w-full flex items-center justify-center mb-4">
              <div className="w-1/6 flex items-center justify-end mr-2">
                <img src={logo} alt="" className="w-[100px]" />
              </div>
              <div className="w-5/6 flex items-center justify-start ml-2">
                <div className="flex justify-center">
                  <h1 className="text-2xl font-bold text-center">
                    <span className="text-purple-700 text-2xl font-bold">AI-Powered </span>
                    <span className="text-xl font-bold text-[#1E2A38]">
                      Match Performance Analysis
                    </span>
                  </h1>
                </div>
              </div>
            </div>
            <div className="w-full flex items-center justify-center">
              {analysisResult ? (
                <ResponseData data={analysisResult} setAnalysisResult={setAnalysisResult}/>
              ) : (
                <RequestForm setAnalysisResult={setAnalysisResult}/>
              )}
            </div>
            </div>
          </ElectricBorder>
        </div>
      </div>
    </div>
  );
}

export default App;
