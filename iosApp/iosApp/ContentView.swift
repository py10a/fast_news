import SwiftUI
import shared

struct ContentView: View {
    @State private var isAboutPresent: Bool = false
    
    var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: .init())
                .navigationTitle("Articles")
                .toolbar {
                    ToolbarItem(placement: .topBarTrailing) {
                        Button {
                            isAboutPresent = true
                        } label: {
                            Label("About", systemImage: "info.circle")
                                .labelStyle(.titleAndIcon)
                        }
                    }
                }.popover(isPresented: $isAboutPresent) {
                    AboutScreen()
                }
        }
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
}
