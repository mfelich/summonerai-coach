export class AnalyzeRequestDto {
  constructor(summonerName, region, rank) {
    this.summonerName = summonerName;
    this.region = region;
    this.rank = rank;
  }
}