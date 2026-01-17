package com.project.essentials.resources

interface CoreStringProvider: StringProvider {

 val unknownErrorMessage: String
 val connectionErrorMessage: String
 val deleteAction: String
 val cancelAction: String

}