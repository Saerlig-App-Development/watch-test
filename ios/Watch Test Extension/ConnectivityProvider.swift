//
//  ConnectivityProvider.swift
//  Watch Test Extension
//
//  Created by Riccardo on 14/09/2020.
//

import WatchConnectivity

class ConnectivityProvider: NSObject, WCSessionDelegate {

    private let session: WCSession

    init(session: WCSession = .default) {
        self.session = session
        super.init()
        self.session.delegate = self
    }

    func send(message: [String:Any]) -> Void {
        session.sendMessage(message, replyHandler: nil) { (error) in
            print(error.localizedDescription)
        }
    }

    func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) {
        // code
    }

}
