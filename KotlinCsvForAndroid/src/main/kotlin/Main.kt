package org.example

import kotlinx.coroutines.*
import org.example.Constants.Language.Default
import org.example.Constants.Platform.Android

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    runBlocking {
        Parser.parseData(Reader.readFile("src/main/resources/android.csv"))
            .forEach { (locale, records) ->
                launch(Dispatchers.IO) {
                    Writer.writeFile(
                        Android,
                        locale,
                        records.filterNot {
                            !it.locale.equalsIgnoreCase(Default) && it.untranslatable
                        }
                    )
                }
            }
    }
}