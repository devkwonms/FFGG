import {useEffect, useReducer, useState} from "react";

function Home(){
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState({});
    const [nickname, setNickname] = useState("");
    
    const getUser = async(nickname) =>{
        const requestOptions ={
            method:'GET'
        }
        const json = await (
            await fetch(
            `http://localhost:8080/api/usersearch/${nickname}`,requestOptions
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