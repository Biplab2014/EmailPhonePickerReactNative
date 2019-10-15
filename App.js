import React, { Component } from 'react';
import { View ,NativeModules,TouchableOpacity,Text,DeviceEventEmitter} from 'react-native';


class App extends Component {
  componentDidMount(){
    this.setEventToGetEmailAndPhonenumber();
  }
  render() {
    return (
      <View>
          <TouchableOpacity style={{alignItems: 'center',backgroundColor: '#DDDDDD',padding: 10, margin:40}}
            onPress={this.getPhoneNumber}>
              <Text> Get Phone Number Here </Text>
          </TouchableOpacity>

          <TouchableOpacity style={{alignItems: 'center',backgroundColor: '#DDDDDD',padding: 10,margin:40}}
            onPress={this.getEmailId}>
              <Text> Get Email Here </Text>
          </TouchableOpacity>
      </View>
    );
  }
  
  setEventToGetEmailAndPhonenumber(){
    DeviceEventEmitter.addListener('user_phone_number', (e: Event) => {
        console.log("User selected phone number==> ",e)
    });

    DeviceEventEmitter.addListener('user_email', (e: Event) => {
      console.log("User selected email id ==> ",e)
    });
  }
  

  getPhoneNumber=()=>{
    console.log("getPhoneNumber ")
    NativeModules.PickEmailAndPhone.getPhoneNumber();
  }

  getEmailId=()=>{
    console.log("getEmailId ")
    NativeModules.PickEmailAndPhone.getEmail();
  }
}

export default App;
