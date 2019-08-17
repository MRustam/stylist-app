import React from 'react'

const LoginPage = () => (

    <div className="ui middle aligned center aligned grid">
        <div className="column">

            <form className="ui large form">
                <div className="ui stacked segment">
                    <div className="field">
                        <div className="ui left icon input">
                            <i className="user icon"/>
                            <input type="text" name="email" placeholder="E-mail address"/>
                        </div>
                    </div>
                    <div className="field">
                        <div className="ui left icon input">
                            <i className="lock icon"/>
                            <input type="password" name="password" placeholder="Password"/>
                        </div>
                    </div>
                    <div className="ui fluid large teal submit button">Login</div>
                </div>
            </form>

        </div>
    </div>

)

export default LoginPage