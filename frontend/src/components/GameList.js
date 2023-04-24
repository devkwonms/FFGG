import { Box, Tab, Tabs } from "@mui/material";
import { useEffect, useState } from "react";
import MatchThumbnail from "./MatchThumbnail";

function GameList({accessId}){
  const [loading, setLoading] = useState(true);
  const [list, setList] = useState([]);
  const [matchType, setMatchType] = useState(50);
  const [offset, setOffset] = useState(0);
  const [numMatches, setNumMatches] = useState(10); // number of matches to display

  const getMatchList = async(matchType,offset) =>{
    const requestOptions = {
        method: 'GET'
    };
    if(matchType === 50||matchType === 40||matchType === 52){
    const json = await (await fetch(`/api/matches/${accessId}/${matchType}/${offset}`, requestOptions)).json();
    setList(json);
    }

    // matchType 에러시 예외처리 구문(미완)
    // else if(matchType === 10){
    //   const json = await (await fetch(`/api/userSearch/호날두`, requestOptions)).json();
    //   setList(json);
    // }
    // else{
    //   console.log("fetch error!");
    // }  
    
  };

  useEffect(()=>{
    getMatchList(matchType,offset);
  },[matchType,offset]);

  const handleLoadMore = () => {
    setOffset(offset + 10); // increase the number of matches to display by 10
  }
  
  // console.log(list?.matchDetailList?.length)

  const spPositions = []; // spPosition 값을 저장할 배열 생성
list?.matchDetailList?.forEach((info) => {
  info?.matchInfo?.forEach((matchInfo) => {
    matchInfo?.player?.forEach((player) => {
      if (player?.spPosition) {
        spPositions.push(player?.spPosition);
      }
    });
  });
});

console.log(spPositions);
  return(
  <div>
      
      <hr />
      <Box>
      <Tabs value={matchType} onChange={(e, newMatchType)=>{setMatchType(newMatchType)}} aria-label="basic tabs example">
        <Tab label="공식경기" value={50} />
        <Tab label="친선경기" value={40} />
        <Tab label="감독모드" value={52} />
        <Tab label="나의 선수랭킹" value={10} />
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
            matchInfo={info?.matchInfo}
            />
        ))}
        </Box>
    </div>
    
    )
}
export default GameList;