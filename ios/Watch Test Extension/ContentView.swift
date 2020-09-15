//
//  ContentView.swift
//  Watch Test Extension
//
//  Created by Riccardo on 14/09/2020.
//

import SwiftUI
import WatchConnectivity

struct ContentView: View {
    
    @State var showMessage: String = "Wating"

    var body: some View {
        Text(self.showMessage)
    }
}

class WatchSessionDelegate: NSObject, WCSessionDelegate {
  var parent: ContentView
  init(_ watchInfo: ContentView) {
    self.parent = watchInfo
  }
   
  func session(_ session: WCSession, didReceiveMessage message: [String : Any], replyHandler: @escaping ([String : Any]) -> Void) {
//    let data: Dictionary<String, String> = message["data"] as! Dictionary<String, String>
    
    parent.showMessage = "got message"
  }
   
  func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) {
  }
   
  func session(_ session: WCSession, didReceiveApplicationContext applicationContext: [String : Any]) {
  }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
