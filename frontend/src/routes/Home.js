import {useEffect, useReducer, useState} from "react";

function Home(){
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState({});
    const [nickname, setNickname] = useState("");
    
    const getUser = async(nickname) =>{
        const requestOptions ={
            method:'GET',
            headers : {
            "Authorization" : "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJYLUFwcC1SYXRlLUxpbWl0IjoiNTAwOjEwIiwiYWNjb3VudF9pZCI6IjE1MjY5MjMzNzAiLCJhdXRoX2lkIjoiMiIsImV4cCI6MTY4ODg4ODQyMSwiaWF0IjoxNjczMzM2NDIxLCJuYmYiOjE2NzMzMzY0MjEsInNlcnZpY2VfaWQiOiI0MzAwMTE0ODEiLCJ0b2tlbl90eXBlIjoiQWNjZXNzVG9rZW4ifQ.SqVNR_woA6kakkt-DtclOu0DP5tJNwgd5q1DDqTde_Q"
            }
        }
        const json = await (
            await fetch(
            `https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname=${nickname}`,requestOptions
        )
        ).json();
        setUser(json);
        setLoading(false);
        };
    // useEffect(() => {
    //     getUser();
    // },[])
    const reset = ()=> {
        setNickname("");
    }
    const onChangeNickname = (e) =>{
        setNickname(e.target.value);
    }
    console.log(nickname);
    return(
    <div>
        <h1>FF.GG</h1>
        <input className="user_input"
        type = "text"
        placeholder="구단주명을 입력하세요!"
        onChange={onChangeNickname}
        value={nickname}
        ></input>
        <button onClick = {reset}>Reset</button>
        <button onClick = {()=>{getUser(nickname)}}>Search</button>
        <ul>
            <li>{user.level}</li>
            <li>{user.accessId}</li>
            <li>{user.nickname}</li>
        </ul>
    </div>
    )
}
export default Home;