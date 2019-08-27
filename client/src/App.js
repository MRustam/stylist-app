import React, {Component} from 'react';
import './App.css';
import { BrowserRouter as Router, Route} from 'react-router-dom'
import AppHeader from './component/AppHeader'
import UserList from './component/UserList'
import CoursePage from './component/CoursePage'
import HomePage from './component/HomePage'
import LoginPage from "./component/LoginPage";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {isAuth: false}
    }

    setAuth = (b) => {
        this.setState({isAuthenticated: b})
    }

    render() {
        return (
            <div className="App ui center aligned container">
                <Router>
                    <AppHeader />
                    <div>
                        <Route exact path="/" component={HomePage} />
                        <Route path="/users" component={UserList} />
                        <Route path="/course" component={CoursePage} />
                        <Route path="/login" render={(props) => <LoginPage{...props}  isAuth={this.isAuth}/>
                            />
                    </div>
                </Router>
            </div>
        )
    }
}
export default App;
