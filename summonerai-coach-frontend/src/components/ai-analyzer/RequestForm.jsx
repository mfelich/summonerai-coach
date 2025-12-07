import React, { useState } from "react";
import Spinner from "../spinner/Spinner";
import { MdOutlineErrorOutline } from "react-icons/md";
import { analyzePlayer } from "../../services/aiAnalyzerService";

const RequestForm = ({ setAnalysisResult }) => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [selectedRank, setSelectedRank] = useState("Select Rank");
  const [summonerName, setSummonerName] = useState("");
  const [region, setRegion] = useState("EUROPE");

  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(false);
  const toggleDropdown = () => setIsDropdownOpen(!isDropdownOpen);

  const ranks = [
    "Bronze",
    "Silver",
    "Gold",
    "Platinum",
    "Emerald",
    "Diamond",
    "Master",
    "Grandmaster",
    "Challenger",
  ];

  const handleSelect = (rank) => {
    setSelectedRank(rank);
    setIsDropdownOpen(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      summonerName,
      region,
      rank: selectedRank,
    };

    try {
      setLoading(true);
      setError(false);

      const result = await analyzePlayer(requestBody);
      setAnalysisResult(result);
    } catch (error) {
      setError(true);
      console.error(error);
    }
  };

  const handleClose = () => {
    setAnalysisResult(null);
    setLoading(false);
    setError(false);
  };

  return (
    <>
      {error ? (
        <div className="space-y-2 my-6 w-full px-16">
          <div className="flex items-center justify-center">
            <MdOutlineErrorOutline className="text-5xl text-red-500"></MdOutlineErrorOutline>
          </div>
          <div className="flex items-center justify-center mb-6">
            <h1 className="text-xl font-semibold text-gray-600">
              Error while anayzing player
            </h1>
          </div>
          <div className="flex items-center justify-center bg-gray-300 py-2 rounded-md hover:cursor-pointer">
            <button className="font-semibold" onClick={handleClose}>
              Try again
            </button>
          </div>
        </div>
      ) : loading ? (
        <div className="space-y-2 my-6 w-full px-16">
          <div className="flex items-center justify-center">
            <Spinner />
          </div>
          <div className="flex items-center justify-center mb-6">
            <h1 className="text-xl font-semibold text-gray-600">Loading...</h1>
          </div>
          <div className="flex items-center justify-center bg-red-500 py-2 rounded-md hover:cursor-pointer">
            <button className="font-semibold" onClick={handleClose}>
              Close
            </button>
          </div>
        </div>
      ) : (
        <form
          className="w-[520px] bg-primary space-y-4 px-4 py-4 rounded-sm shadow-2xl"
          onSubmit={handleSubmit}
        >
          {/* Summoner name input */}
          <div>
            <label
              htmlFor="summoner_name"
              className="block mb-2.5 text-md font-semibold text-heading text-white"
            >
              Summoners name
            </label>
            <input
              type="text"
              id="summoner_name"
              className="bg-gray-200 text-black font-semibold border-default-medium text-heading text-sm rounded-base focus:ring-4 focus:ring-brand-medium focus:outline-none shadow-xsleading-5 block w-full px-3 py-2.5 shadow-xs placeholder-gray-400 rounded-md"
              placeholder="DoubleAIM"
              required
              value={summonerName}
              onChange={(e) => setSummonerName(e.target.value)}
            />
          </div>

          {/* Region radio */}
          <div className="flex justify-between items-start">
            <label
              htmlFor="region"
              className="block text-md font-semibold text-heading text-white"
            >
              Input your region
            </label>
            <div className="flex items-center justify-start space-x-4">
              <div className="flex items-center">
                <input
                  id="country-option-1"
                  type="radio"
                  name="countries"
                  value="EUROPE"
                  checked={region === "EUROPE"}
                  onChange={() => setRegion("EUROPE")}
                  className="w-4 h-4 rounded-full accent-purple-600"
                />
                <label
                  htmlFor="country-option-1"
                  className="ms-2 text-sm font-medium text-heading text-white"
                >
                  Europe
                </label>
              </div>
              <div className="flex items-center">
                <input
                  id="country-option-2"
                  type="radio"
                  name="countries"
                  value="ASIA"
                  checked={region === "ASIA"}
                  onChange={() => setRegion("ASIA")}
                  className="w-4 h-4 rounded-full accent-purple-600"
                />
                <label
                  htmlFor="country-option-2"
                  className="ms-2 text-sm font-medium text-heading text-white"
                >
                  Asia
                </label>
              </div>
              <div className="flex items-center">
                <input
                  id="option-disabled"
                  type="radio"
                  name="countries"
                  value="CHINA"
                  checked={region === "CHINA"}
                  onChange={() => setRegion("CHINA")}
                  className="w-4 h-4 rounded-full accent-purple-600"
                />
                <label
                  htmlFor="option-disabled"
                  className="ms-2 text-sm font-medium text-fg-disabled text-gray-200"
                >
                  China
                </label>
              </div>
            </div>
          </div>

          {/* Rank dropdown */}
          <div>
            <label className="block mb-2.5 text-md font-semibold text-heading text-white">
              Rank
            </label>
            <div className="relative">
              <button
                type="button"
                onClick={toggleDropdown}
                className="inline-flex items-center bg-white rounded-md font-semibold justify-between w-full text-black bg-brand box-border borderfocus:outline-none hover:bg-brand-strong focus:ring-4 focus:ring-brand-medium shadow-xsleading-5 rounded-base text-sm px-4 py-2.5 mb-4 focus:outline-none"
              >
                {selectedRank}
                <svg
                  className="w-4 h-4 ms-1.5 -me-0.5"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="none"
                  viewBox="0 0 24 24"
                >
                  <path
                    stroke="currentColor"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="m19 9-7 7-7-7"
                  />
                </svg>
              </button>

              {isDropdownOpen && (
                <ul
                  className="absolute z-20 w-full bg-white border border-default-medium rounded-lg shadow-lg max-h-60 overflow-y-auto 
                  [&::-webkit-scrollbar]:w-2
                [&::-webkit-scrollbar-track]:bg-gray-300
                  [&::-webkit-scrollbar-track]:rounded-md
                [&::-webkit-scrollbar-thumb]:bg-purple-400
                  [&::-webkit-scrollbar-thumb]:rounded-md"
                >
                  {ranks.map((rank) => (
                    <li key={rank}>
                      <button
                        type="button"
                        onClick={() => handleSelect(rank)}
                        className="w-full text-left px-4 py-2 hover:bg-neutral-tertiary-medium hover:text-heading"
                      >
                        {rank}
                      </button>
                    </li>
                  ))}
                </ul>
              )}
            </div>
          </div>

          {/* Submit button */}
          <div className="w-full">
            <button
              type="submit"
              className="bg-purple-700 text-white w-full py-2 rounded-sm hover:cursor-pointer"
            >
              Analyze
            </button>
          </div>
        </form>
      )}
    </>
  );
};

export default RequestForm;
