import React, {Component} from 'react'
import api from '../axios-config'

class BlogPage extends Component {

    handleUpload = () => {
        const formData = new FormData();
        api.post('/upload-multiple-files',
                {headers: {'Content-Type': 'multipart/form-data'}
            })
    }

    render() {
        return (
            <div>
                <input type="file" multiple onChange={this.handleUpload}/>
            </div>
        )
    }
}

export default BlogPage