import { Box, Tab, Tabs } from "@mui/material";
import { useEffect, useState } from "react";
import MatchThumbnail from "./MatchThumbnail";

function GameList({accessId}){
  const [loading, setLoading] = useState(true);
  const [list, setList] = useState([]);
  const [matchType, setMatchType] = useState(50);
  const [offset, setOffset] = useState(0);
  const [limit, setLimit] = useState(10);

  const getMatchList = async(matchType,offset) =>{
    const requestOptions = {
        method: 'GET'
    };
    if(matchType === 50||matchType === 40||matchType === 52){
    const json = await (await fetch(`/api/matches?accessId=${accessId}&matchtype=${matchType}&offset=${offset}&limit=${limit}`, requestOptions)).json();
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
  console.log();
  useEffect(()=>{
    getMatchList(matchType,offset);
  },[matchType,offset]);

  const handleLoadMore = () => {
    setOffset(offset + 10); // increase the number of matches to display by 10
  }
  // const handleLoadMore = async () => {
  //   const newOffset = offset + numMatches; // 새로운 offset 값 계산
  //   const newMatches = await getMatchList(matchType, newOffset); // 새로운 경기 목록 가져오기
  //   setList(prevList => [...prevList, ...newMatches]); // 기존 목록에 새로운 경기 추가
  //   setOffset(newOffset); // offset 값 업데이트
  // }
  return(
  <div>
      
      <hr />
      <Box>
      <Tabs value={matchType} onChange={(e, newMatchType)=>{setMatchType(newMatchType)}} aria-label="basic tabs example">
        <Tab label="공식경기" value={50} />
        <Tab label="친선경기" value={40} />
        <Tab label="감독모드" value={52} />
        {/* <Tab label="나의 선수랭킹" value={10} /> */}
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
        <button onClick={handleLoadMore}>더보기</button>
        </Box>
    </div>
    
    )
}
export default GameList;