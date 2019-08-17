import React, {Component} from 'react'
import 'video-react/dist/video-react.css'
import { Player } from 'video-react'

class CoursePage extends Component {
    render() {
        return (
            <div>
                <Player playsInline src="https://media.w3.org/2010/05/sintel/trailer_hd.mp4" />
            </div>
        )
    }
}

export default CoursePage