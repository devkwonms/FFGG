import {useEffect, useReducer, useState} from "react";
import {useParams} from "react-router-dom";
import styled from 'styled-components';
import {Box, Container, Divider, Stack, Tab, Tabs} from "@mui/material"

import TopTierList from "../components/TopTierList";
import Footer from "../components/Footer";
import Navigation from '../components/Navigation';
import GameList from "../components/GameList";


function Detail(){
    const {nickname} = useParams();
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState({});
    const [pagination, setPagination] = useState({});
    
    const getUser = async() =>{
        const requestOptions ={
            method:'GET'
        }
        const json = await (
            await fetch(
            `/api/userSearch?nickname = ${nickname}`,requestOptions
        )
        ).json();
        setUser(json);
        setLoading(false);
        
    };
    // user의 값이 존재할때만 getUser(); => api 실행
    useEffect(()=>{
        if (user) {
            getUser();
        }
        },[])
        // useEffect(() => {
        //     const {startIndex, endIndex} = pagination;
        //     setPagination({
        //       startIndex: startIndex+10,
        //       endIndex: endIndex+10,
        //     })
        //   }, [pagination]);
        // console.log(user.userSearchDto)
        // user.accessId 가 null 이면 "해당 구단주는 존재하지 않습니다! 로직 구현(isEmpty 쓰면될듯)"
        console.log(user);
    console.log(user?.userSearchDto?.accessId);
    return(
    <div>
        <Navigation/>
        <Container  maxWidth="xl">
            <Stack sx={{background:"ivory"}} direction={"row"}>
        <Header >
            <ul>
                <div>{user?.userSearchDto?.nickName}</div>
                <div>레벨 : {user?.userSearchDto?.level}</div>
            </ul>
        </Header>   

            <Stack justifyContent={"space-around"} flex direction={"row"}>
        {user?.topTierList?.map((info,i) => (
            <TopTierList
            key={i}
            matchType = {info?.matchType}
            division = {info?.division}
            achievementDate = {info?.achievementDate}
            divisionImgUrl = {info?.divisionImgUrl}
            />
        ))}
            </Stack>
            </Stack>
        <Box>
        {user.userSearchDto &&<GameList accessId={user?.userSearchDto?.accessId}/>}
        </Box>
        <hr />
        </Container>
        <Footer/>
    </div>
    
    )
}
export default Detail;

const Header = styled.header`
max-width: 1200px;
width: 95%;
height: 80px;
display: flex;
flex-direction: row;
-webkit-box-align: center;
align-items: center;
-webkit-box-pack: justify;
justify-content: space-between;
margin: 0px auto;
`