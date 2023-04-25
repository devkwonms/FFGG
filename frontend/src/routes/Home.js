import {useEffect, useReducer, useState} from "react";
import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import bgImg from '../image/messi.jpg';
import Navigation from '../components/Navigation';
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat"></link>

const Home = () => {
  const navigate = useNavigate();
  // const [summonerName, setSummonerName] = useState('');
  // const history = useNavigate();
  // const searchSummoner = (e) => {
  //   e.preventDefault();
  //   history.push(`/summoner?summonerName=${summonerName}`);
  // }
  const [input, setInput] = useState("delariva");
  const reset = ()=> {
    setInput("");
}
const onChangeInput = (e) =>{
  setInput(e.target.value);
}
console.log(input);

  const handleOnClick = (input) => {
    navigate(`/user/${input}`);
  };
const handleOnKeyPress = e => {
  if (e.key === 'Enter') {
    console.log(e.key);
    handleOnClick(input);
  }
};
  
    return (
        <HomeContainer>
          <Navigation/>
            <FormContainer>
                <Form>
                    <Input
                      value={input}
                      onChange={onChangeInput}
                      placeholder="구단주명을 입력해주세요!"
                      type="text"
                      onKeyPress={handleOnKeyPress}
                    />
                    <Button onClick={reset}>Reset</Button>
                    <Link to ={`/user/${input}`}>
                    <Button >조회</Button>
                    </Link>
                </Form>
            </FormContainer>
        </HomeContainer>
    )
  
}

export default Home;


const HomeContainer = styled.div`
  background-image: url('${bgImg}');
  background-repeat: no-repeat;
  background-position: top center;
  background-size: cover;
  background-attachment: fixed;
  height: 100vh;
  margin: 0 auto;
  opacity : 0.9;
`
// logo img
const HomeMainImg = styled.img`
  max-width: 360px;
  max-height: 100px;
  margin: 0 auto;
  margin-left: 1rem;
  margin-top: 2rem;
`

const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const Form = styled.form`
  width: 664px;
  height: 54px;
  position: relative;
  z-index: 5;
  margin-top: 10%;
  overflow: hidden;
  padding: 0px;
  border-radius: 8px;
  border: 1px solid #48526A;
  background-color: white;
`

const Input = styled.input`
  outline: none;
  box-sizing: border-box;
  background-color: transparent;
  position: relative;
  padding-left: 24px;
  z-index: 11;
  width: 80%;
  height: 100%;
  line-height: 54px;
  font-weight: 500;
  letter-spacing: 0.5px;
  font-size: 1.4em;
  color: rgb(137, 160, 181);
  border-width: initial;
  border-style: none;
  border-color: initial;
  border-image: initial;
`

const Button = styled.button`
display: flex;
    position: absolute;
    top: 0;
    right: 0;
    margin: 10px 10px 0 0;
    height: 30px;
    padding: 0;
    border: none;
    color: #141D33;
    width: 70px;
    font-size: 18px;
    font-weight: 600;
    background-color: #C8CFDB;;
    justify-content: center;
    outline: none;
    border-radius: 5px;
`

const LogoText = styled.h3`
padding-left: 2%;
color : white;
font-family: 'Montserrat', sans-serif;
`