import React, {Component} from 'react'
import api from '../axios-config'
import {toast, ToastContainer} from "react-toastify";

class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''}
    }

    handleChange = (event) => {
        this.setState({[event.target.name]: event.target.value})
    }

    performLogin = () => {
        const user = {username: this.state.username, password: this.state.password}
        api.post('/login', user)
            .then(res => {
                const jwt = res.headers.authorization
                if (jwt !== null) {
                    sessionStorage.setItem('jwt', jwt)
                    sessionStorage.setItem('isAuthenticated', 'true')
                    toast.success("Успешно.", {
                        position: toast.POSITION.TOP_RIGHT
                    })
                    this.props.setAuthenticated(true)
                }
            }).catch(err => {
            toast.error("Неверные логин или пароль", {
                position: toast.POSITION.TOP_RIGHT
            })
        })
    }

    performLogout = () => {
        sessionStorage.removeItem('jwt')
        sessionStorage.removeItem('isAuthenticated')
        this.props.setAuthenticated(false)
    }

    render() {
        let authenticated = this.props.isAuthenticated;
        return (
            <div>

                <div>
                    <div className="ui middle aligned center aligned grid">
                        <div className="column">
                            {authenticated ? <div className="ui button" onClick={this.performLogout}>Logout</div> :
                                <form className="ui large form">
                                    <div className="ui stacked segment">
                                        <div className="field">
                                            <div className="ui left icon input">
                                                <i className="user icon"/>
                                                <input type="email" name="username" placeholder="E-mail address"
                                                       onChange={this.handleChange}/>
                                            </div>
                                        </div>
                                        <div className="field">
                                            <div className="ui left icon input">
                                                <i className="lock icon"/>
                                                <input type="password" name="password" placeholder="Password"
                                                       onChange={this.handleChange}/>
                                            </div>
                                        </div>
                                        <div className="ui fluid large teal submit button"
                                             onClick={this.performLogin}>Login
                                        </div>
                                    </div>
                                </form>
                            }
                        </div>
                    </div>

                    <ToastContainer autoClose={3000}/>
                </div>

            </div>
        )
    }
}

export default LoginPage