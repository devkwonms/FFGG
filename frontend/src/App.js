import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./routes/Home";
import Detail from "./routes/Detail";
import Error from "./components/Error";
import Navigation from "./components/Navigation";
import GameList from "./components/GameList";
import SoccerField from "./components/field/SoccerField";

function App() {
  return <Router>
    <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/user/:nickname" element={<Detail />} />
        <Route path="/error" element={<Error/>} />
        {/* <Route path="/navi" element={<Navigation/>} /> */}
        {/* <Route path="/list" element={<GameList />} /> */}
        <Route path="/field" element={<SoccerField />} />
    </Routes>
</Router>;
}

export default App;
