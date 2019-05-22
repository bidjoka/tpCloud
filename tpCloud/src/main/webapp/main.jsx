
// Code goes here
// tried on https://reactarmory.com
// JSX file...

class MyApp extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      data: [],
    };
  }

  componentDidMount() {
    fetch('https://my-cloud-project-232813.appspot.com/_ah/api/myApi/v1/petitions')
      .then(response => response.json())
      .then(data => this.setState({  data : data.items }));
  }
  render() {
    return <ul>{this.state.data.map(e => <li> {e.properties.name} {e.properties.score} </li>)}  </ul>;
  }
}

ReactDOM.render(
React.createElement('div', null,
    React.createElement(MyApp)
  )
,
  document.getElementById('app')
);

