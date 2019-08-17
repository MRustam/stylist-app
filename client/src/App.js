import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route} from 'react-router-dom'
import AppHeader from './component/AppHeader'
import UserList from './component/UserList'
import CoursePage from './component/CoursePage'
import HomePage from './component/HomePage'
import LoginPage from "./component/LoginPage";

function App() {
    return (
        <div className="App ui center aligned container">
            <Router>
                <AppHeader />
                <div>
                    <Route exact path="/" component={HomePage} />
                    <Route path="/users" component={UserList} />
                    <Route path="/course" component={CoursePage} />
                    <Route path="/login" component={LoginPage}/>
                </div>
            </Router>
        </div>
    );
}
export default App;
