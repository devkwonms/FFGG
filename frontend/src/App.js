import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./routes/Home";
import Detail from "./routes/Detail";
import Error from "./components/Error";
import GameList from "./components/GameList";
import SoccerField from "./components/field/SoccerField";
import SimpleAccordion from "./components/SimpleAccordion";

function App() {
  return <Router>
    <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/user/:nickname" element={<Detail />} />
        <Route path="/error" element={<Error/>} />
        <Route path="/field" element={<SoccerField />} />
        <Route path="/acco" element={<SimpleAccordion />} />
    </Routes>
</Router>;
}

export default App;
