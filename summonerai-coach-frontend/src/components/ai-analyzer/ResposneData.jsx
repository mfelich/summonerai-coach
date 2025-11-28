import React from "react";
import { GrLinkTop } from "react-icons/gr";

const ResponseData = ({ data, setAnalysisResult }) => {
  const navigateToTop = () => {
    const element = document.getElementById("first_element");
    if (element) {
      element.scrollIntoView({ behavior: "smooth" });
    }
  };

  const handleClose = () => {
    setAnalysisResult(null);
  };

  return (
    <>
      <div className="w-[520px] bg-primary space-y-8 px-2 py-4 rounded-sm shadow-2xl">
        <div
          className="text-white space-y-4 max-h-[500px] overflow-y-auto p-4   [&::-webkit-scrollbar]:w-2
  [&::-webkit-scrollbar-track]:bg-gray-300
  [&::-webkit-scrollbar-track]:rounded-md
  [&::-webkit-scrollbar-thumb]:bg-purple-400
    [&::-webkit-scrollbar-thumb]:rounded-md"
        >
          <h2 className="text-2xl font-bold text-purple-400" id="first_element">
            Summary
          </h2>
          <p className="text-white font-semibold">{data.summary}</p>

          <h2 className="text-xl font-bold text-purple-400">
            Playstyle Profile
          </h2>
          <p className="text-white font-semibold">{data.playstyleProfile}</p>

          <h2 className="text-xl font-bold text-purple-400">Strengths</h2>
          <ul className="list-disc pl-5 text-white font-semibold">
            {data.strengths?.map((s, i) => (
              <li key={i}>{s}</li>
            ))}
          </ul>

          <h2 className="text-xl font-bold text-purple-400">Weaknesses</h2>
          <ul className="list-disc pl-5 text-white font-semibold">
            {data.weaknesses?.map((w, i) => (
              <li key={i}>{w}</li>
            ))}
          </ul>

          <h2 className="text-xl font-bold text-purple-400">
            Consistency Issues
          </h2>
          <ul className="list-disc pl-5 text-white font-semibold">
            {data.consistencyIssues?.map((c, i) => (
              <li key={i}>{c}</li>
            ))}
          </ul>

          <h2 className="text-xl font-bold text-purple-400">Early Game</h2>
          <p className="text-white font-semibold">{data.earlyGameAnalysis}</p>

          <h2 className="text-xl font-bold text-purple-400">Mid Game</h2>
          <p className="text-white font-semibold">{data.midGameAnalysis}</p>

          <h2 className="text-xl font-bold text-purple-400">Late Game</h2>
          <p className="text-white font-semibold">{data.lateGameAnalysis}</p>

          <h2 className="text-xl font-bold text-purple-400">Recommendations</h2>
          <ul className="list-disc pl-5 text-white font-semibold">
            {data.recommendations?.map((r, i) => (
              <li key={i}>{r}</li>
            ))}
          </ul>

          <div className="flex items-center justify-center space-x-4 mt-12">
            <div className="w-1/2 flex items-center justify-center bg-white py-2 rounded-md space-x-2 hover:cursor-pointer">
              <GrLinkTop className="bg-purple-400"></GrLinkTop>
              <button
                className="hover:cursor-pointer text-[#1E2A38] font-semibold"
                onClick={navigateToTop}
              >
                Back on top
              </button>
            </div>
            <div className="w-1/2 flex items-center justify-center bg-red-500 py-2 rounded-md hover:cursor-pointer">
              <button className="hover:cursor-pointer font-semibold" onClick={handleClose}>
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ResponseData;
