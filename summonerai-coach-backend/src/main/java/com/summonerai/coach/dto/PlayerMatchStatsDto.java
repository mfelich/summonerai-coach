package com.summonerai.coach.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerMatchStatsDto {

    @JsonProperty("puuid")
    private String puuid;

    @JsonProperty("championName")
    private String championName;

    @JsonProperty("lane")
    private String lane;

    @JsonProperty("win")
    private boolean win;

    //player main stats
    @JsonProperty("kills")
    private int kills;

    @JsonProperty("deaths")
    private int deaths;

    @JsonProperty("assists")
    private int assists;


    //damage
    @JsonProperty("totalDamageDealt")
    private Integer totalDamageDealt;
    @JsonProperty("totalDamageDealtToChampions")
    private Integer totalDamageDealtToChampions;
    @JsonProperty("totalDamageTaken")
    private Integer totalDamageTaken;

    //turret
    @JsonProperty("damageDealtToTurrets")
    private Integer damageDealtToTurrets;
    @JsonProperty("turretKills")
    private Integer turretKills;
    @JsonProperty("turretTakedowns")
    private Integer turretTakedowns;
    @JsonProperty("turretsLost")
    private Integer turretsLost;
    @JsonProperty("killsNearEnemyTurret")
    private Integer killsNearEnemyTurret;

    //minions
    @JsonProperty("totalMinionsKilled")
    private Integer totalMinionsKilled;
    @JsonProperty("neutralMinionsKilled")
    private Integer neutralMinionsKilled;

    //dragon/baron
    @JsonProperty("baronKills")
    private Integer baronKills;
    @JsonProperty("dragonKills")
    private Integer dragonKills;

    //multi-kills
    @JsonProperty("doubleKills")
    private Integer doubleKills;
    @JsonProperty("tripleKills")
    private Integer tripleKills;
    @JsonProperty("quadraKills")
    private Integer quadraKills;
    @JsonProperty("pentaKills")
    private Integer pentaKills;

    //vision
    @JsonProperty("visionScore")
    private Integer visionScore;

    //pings
    @JsonProperty("assistMePings")
    private Integer assistMePings;
    @JsonProperty("commandPings")
    private Integer commandPings;
    @JsonProperty("enemyMissingPings")
    private Integer enemyMissingPings;
    @JsonProperty("getBackPings")
    private Integer getBackPings;
    @JsonProperty("onMyWayPings")
    private Integer onMyWayPings;
    @JsonProperty("pushPings")
    private Integer pushPings;

    //gold
    @JsonProperty("goldPerMinute")
    private float goldPerMinute;
    @JsonProperty("goldEarned")
    private Integer goldEarned;
    @JsonProperty("goldSpent")
    private Integer goldSpent;


}
