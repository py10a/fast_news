//
//  AboutScreen.swift
//  iosApp
//

import shared
import SwiftUI

struct AboutScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logInfo()
        
        var result: [RowItem] = [
            .init(
                title: "OS",
                subtitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device model",
                subtitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subtitle: "\(platform.density)"
            )
        ]
        return result
    }()
    
    var body: some View {
        NavigationStack {
            List {
                ForEach(items, id: \.self) { item in
                    VStack(alignment: .leading){
                        Text(item.title)
                            .font(.footnote)
                            .foregroundStyle(.secondary)
                        Text(item.subtitle)
                            .font(.body)
                            .foregroundStyle(.primary)
                    }
                }
            }
            .navigationTitle("About")
            .toolbar {
                ToolbarItem {
                    Button {
                        dismiss()
                    } label: {
                        Text("Return")
                    }
                }
            }
        }

    }
}

#Preview {
    AboutScreen()
}
