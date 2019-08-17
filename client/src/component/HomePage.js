import React, {Component} from 'react'
import Faker from 'faker'

class HomePage extends Component {
    render() {
        return (
            <div className="ui divided items">

                <div id="item" className="item">
                    <div className="ui grid">
                        <div className="seven wide column">
                            <div className="ui small image">
                                <img src={Faker.image.avatar()}/>
                            </div>
                        </div>
                        <div className="nine wide column">
                            <div className="middle aligned content">
                                <div className="header">
                                    {Faker.lorem.sentence()}
                                </div>
                                <div className="extra">
                                    <div className="ui right floated primary button">
                                        Интересно
                                        <i className="right chevron icon"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="item" className="item">
                    <div className="ui grid">
                        <div className="seven wide column">
                            <div className="ui small image">
                                <img src={Faker.image.avatar()}/>
                            </div>
                        </div>
                        <div className="nine wide column">
                            <div className="middle aligned content">
                                <div className="header">
                                    {Faker.lorem.sentence()}
                                </div>
                                <div className="extra">
                                    <div className="ui right floated primary button">
                                        Интересно
                                        <i className="right chevron icon"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}

export default HomePage