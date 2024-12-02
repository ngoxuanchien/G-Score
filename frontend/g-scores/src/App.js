import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import styled from "styled-components";
import Navbar from "./components/navbar/Navbar";
import Sidebar from "./components/sidebar/Sidebar";
import Dashboard from "./pages/dashboard/Dashboard";
import SearchScores from "./pages/searchscore/SearchScores";
import Reports from "./pages/report/Reports";
import Settings from "./pages/setting/Settings";

const AppContainer = styled.div`
  display: flex;
`;

const MainContent = styled.div`
  flex: 1;
  padding-left: 200px;
  padding-top: 20px;
`;

function App() {
  return (
    <Router>
      <Navbar />
      <AppContainer>
        <Sidebar />
        <MainContent>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/search-scores" element={<SearchScores />} />
            <Route path="/reports" element={<Reports />} />
            <Route path="/settings" element={<Settings />} />
          </Routes>
        </MainContent>
      </AppContainer>
    </Router>
  );
}

export default App;
