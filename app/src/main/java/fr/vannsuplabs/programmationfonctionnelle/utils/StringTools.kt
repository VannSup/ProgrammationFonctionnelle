package fr.vannsuplabs.programmationfonctionnelle.utils

class StringTools {

    private val alphabet  = ('a'..'z')

    // Compte le nombre d'occurence par lettre
    private fun countOccurrencesByLetter(text :String ): String{
        var result = ""
        alphabet.forEach { letter -> result += "$letter = ${text.lowercase().filter { myText: Char -> myText == letter }.length} \n" }
        return result + "\n"
    }

    // liste Lettre / Mots qui contiens la lettre
    private fun wordContainsLetters(text: String): String{
        var result = ""
        alphabet.forEach { letter -> result += "$letter = ${mapOfWord(text).filter { myText: String -> myText.contains(letter) }.toHashSet()} \n" }
        return result + "\n"
    }

    // compter le nombre de mots
    private fun countNumberOfWord(text: String): Int {
        return Regex("\\w+").findAll(text).count()
    }

    // Liste des mots dans un texte
    private fun mapOfWord(text: String): HashSet<String> {
        val allWord = HashSet<String>()
        Regex("\\w+").findAll(text.lowercase()).forEach { matchResult: MatchResult -> allWord.add(matchResult.value) }
        return allWord
    }

    // Compte le nombre de char sans space
    private fun countNumberOfChar(text: String): Int {
        return text.filter { c: Char -> c != ' ' }.length
    }

    // Fonction Pure (juste un concate)
    private fun countWordFormatString(count: Int): String{
        return "Nombres de mots : $count \n"
    }

    // Fonction Pure (juste un concate)
    private fun countCharFormatString(count: Int): String{
        return "Nombres de charactere : $count \n"
    }

    // Fonction Pure (juste un concate)
    private fun formatTextWithTitle(text: String, titre: String): String{
        return "Titre : $titre\n" +
                "Texte : $text\n"
    }

    // Concate des différent résultas
    fun showDetailAboutText(text: String, titre: String = "Mon texte"): String{
        return countWordFormatString(countNumberOfWord(text)) +
                countCharFormatString(countNumberOfChar(text)) +
                formatTextWithTitle(text, titre) +
                countOccurrencesByLetter(text) +
                wordContainsLetters(text)
    }

}