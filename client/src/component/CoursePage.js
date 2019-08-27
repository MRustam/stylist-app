import React, {Component} from 'react'
import 'video-react/dist/video-react.css'
import {Player} from 'video-react'
import Faker from 'faker'

class CoursePage extends Component {
    render() {
        return (
            <div>
                <h3>Видосы</h3>
                <div className="ui divider"/>
                <div className="ui cards">

                    <div className="ui centered card">
                        <div className="image">
                            <Player playsInline poster={Faker.image.fashion()}
                                    src="https://media.w3.org/2010/05/sintel/trailer_hd.mp4"/>
                        </div>
                        <div className="extra content">
                                <span className="left floated">
                                    <i className="heart icon"/>
                                    37
                                </span>
                            <span className="right floated">
                                    День первый
                                </span>
                        </div>
                    </div>

                    <div className="ui centered card">
                        <div className="image">
                            <Player playsInline poster={Faker.image.fashion()}
                                    src="https://media.w3.org/2010/05/sintel/trailer_hd.mp4"/>
                        </div>
                        <div className="extra content">
                                <span className="left floated">
                                    <i className="heart icon"/>
                                    25
                                </span>
                            <span className="right floated">
                                    День второй
                                </span>
                        </div>
                    </div>

                    <div className="ui centered card">
                        <div className="image">
                            <Player playsInline poster={Faker.image.fashion()}
                                    src="https://media.w3.org/2010/05/sintel/trailer_hd.mp4"/>
                        </div>
                        <div className="extra content">
                                <span className="left floated">
                                    <i className="heart icon"/>
                                    25
                                </span>
                            <span className="right floated">
                                    День третий
                                </span>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default CoursePage