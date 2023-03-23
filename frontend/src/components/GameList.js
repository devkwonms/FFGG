import {useEffect, useReducer, useState} from "react";
import styled from 'styled-components';
import {Box, Container, Divider, Stack, Tab, Tabs} from "@mui/material"

import Footer from "../components/Footer";
import MatchThumbnail from "./MatchThumbnail";
import Navigation from '../components/Navigation';
import UserInfo from '../components/UserInfo';


function GameList({accessId}){
  const [loading, setLoading] = useState(true);
  const [list, setList] = useState([]);
  const [matchType, setMatchType] = useState(50);
  const [offset, setOffset] = useState(10);

  const getMatchList = async(matchType) =>{
    const requestOptions = {
        method: 'GET'
    };
    const json = await (await fetch(`/api/matches/${accessId}/${matchType}/${offset}`, requestOptions)).json();
  
    setList(json);
  };

  useEffect(()=>{
    getMatchList(matchType);
  },[matchType]);
  
  return(
  <div>
      
      <hr />
      <Box>
      <Tabs value={matchType} onChange={(e, newMatchType)=>{setMatchType(newMatchType)}} aria-label="basic tabs example">
        <Tab label="공식경기" value={50} />
        <Tab label="친선경기" value={40} />
        <Tab label="감독모드" value={52} />
      </Tabs>
        {list?.matchDetailList?.map((info) => (
            <MatchThumbnail
            key={info?.matchId}
            matchDate={info?.matchDate.substring(0,16).replace("T"," ")}
            myResult={accessId === info?.matchInfo[0].accessId ? info?.matchInfo[0]?.matchDetail?.matchResult:info?.matchInfo[1]?.matchDetail?.matchResult}
            leftNickName={info?.matchInfo[0]?.nickname}
            rightNickname={info?.matchInfo[1]?.nickname}
            leftScore={info?.matchInfo[0]?.shoot?.goalTotal}
            rightScore={info?.matchInfo[1]?.shoot?.goalTotal}
            />
        ))}
        </Box>
    </div>
    
    )
}
export default GameList;